# multiAgents.py
# --------------
# Licensing Information:  You are free to use or extend these projects for 
# educational purposes provided that (1) you do not distribute or publish 
# solutions, (2) you retain this notice, and (3) you provide clear 
# attribution to UC Berkeley, including a link to 
# http://inst.eecs.berkeley.edu/~cs188/pacman/pacman.html
# 
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero 
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and 
# Pieter Abbeel (pabbeel@cs.berkeley.edu).

from util import manhattanDistance
from game import Directions
import random, util
import pdb

from game import Agent


class ReflexAgent(Agent):
    """
      A reflex agent chooses an action at each choice point by examining
      its alternatives via a state evaluation function.
      The code below is provided as a guide.  You are welcome to change
      it in any way you see fit, so long as you don't touch our method
      headers.
    """

    def getAction(self, gameState):
        """
        You do not need to change this method, but you're welcome to.
        getAction chooses among the best options according to the evaluation function.
        Just like in the previous project, getAction takes a GameState and returns
        some Directions.X for some X in the set {North, South, West, East, Stop}
        """
        # Collect legal moves and successor states
        legalMoves = gameState.getLegalActions()

        # Choose one of the best actions
        scores = [self.evaluationFunction(gameState, action) for action in legalMoves]
        bestScore = max(scores)
        bestIndices = [index for index in range(len(scores)) if scores[index] == bestScore]
        chosenIndex = random.choice(bestIndices)  # Pick randomly among the best

        "Add more of your code here if you want to"

        return legalMoves[chosenIndex]

    def evaluationFunction(self, currentGameState, action):
        """
        Design a better evaluation function here.
        The evaluation function takes in the current and proposed successor
        GameStates (pacman.py) and returns a number, where higher numbers are better.
        The code below extracts some useful information from the state, like the
        remaining food (newFood) and Pacman position after moving (newPos).
        newScaredTimes holds the number of moves that each ghost will remain
        scared because of Pacman having eaten a power pellet.
        Print out these variables to see what you're getting, then combine them
        to create a masterful evaluation function.
        """
        # Useful information you can extract from a GameState (pacman.py)
        oldFood = currentGameState.getFood().asList();
        successorGameState = currentGameState.generatePacmanSuccessor(action)
        newPos = successorGameState.getPacmanPosition()
        newFood = successorGameState.getFood()
        newGhostStates = successorGameState.getGhostStates()
        newScaredTimes = [ghostState.scaredTimer for ghostState in newGhostStates]

        """
        distanceToGhost = []
        distanceToFood = []
        for ghost in newGhostStates:
            distanceToGhost.append(manhattanDistance(newPos, ghost.getPosition()))
        for food in oldFood:
            distanceToFood.append(manhattanDistance(newPos, food))
        return max(distanceToGhost) - min(distanceToFood)
        """

        value = 0
        for ghost in newGhostStates:
            distanceToGhost = manhattanDistance(newPos, ghost.getPosition())
            if distanceToGhost == 0:
                if newScaredTimes[0]:
                    value += 200
                    newScaredTimes[0] -= 1
                else:
                    value -= 500

        for food in oldFood:
            if food:
                distanceToFood = manhattanDistance(newPos, food)
                if distanceToFood == 0:
                    value += 10
                else:
                    value += 1.0 / (distanceToFood * distanceToFood)

        return value


def scoreEvaluationFunction(currentGameState):
    """
      This default evaluation function just returns the score of the state.
      The score is the same one displayed in the Pacman GUI.

      This evaluation function is meant for use with adversarial search agents
      (not reflex agents).
    """
    return currentGameState.getScore()

class MultiAgentSearchAgent(Agent):
    """
      This class provides some common elements to all of your
      multi-agent searchers.  Any methods defined here will be available
      to the MinimaxPacmanAgent, AlphaBetaPacmanAgent & ExpectimaxPacmanAgent.

      You *do not* need to make any changes here, but you can if you want to
      add functionality to all your adversarial search agents.  Please do not
      remove anything, however.

      Note: this is an abstract class: one that should not be instantiated.  It's
      only partially specified, and designed to be extended.  Agent (game.py)
      is another abstract class.
    """

    def __init__(self, evalFn = 'scoreEvaluationFunction', depth = '2'):
        self.index = 0 # Pacman is always agent index 0
        self.evaluationFunction = util.lookup(evalFn, globals())
        self.depth = int(depth)

class MinimaxAgent(MultiAgentSearchAgent):
    """
      Your minimax agent (question 2)
    """

    def getAction(self, gameState):
        """
          Returns the minimax action from the current gameState using self.depth
          and self.evaluationFunction.

          Here are some method calls that might be useful when implementing minimax.

          gameState.getLegalActions(agentIndex):
            Returns a list of legal actions for an agent
            agentIndex=0 means Pacman, ghosts are >= 1

          gameState.generateSuccessor(agentIndex, action):
            Returns the successor game state after an agent takes an action

          gameState.getNumAgents():
            Returns the total number of agents in the game
        """

        def maxi(gameState, depth):
            if gameState.isWin() or gameState.isLose():
                return self.evaluationFunction(gameState)
            legalActions = gameState.getLegalActions()
            scores = []
            for action in legalActions:
                scores.append(mini(gameState.generateSuccessor(0, action), depth, 1))
            bestActions = []
            for i in range(len(legalActions)):
                if scores[i] == max(scores):
                    bestActions.append(legalActions[i])
            if depth == 0:
                return random.choice(bestActions)
            else:
                return max(scores)

        def mini(gameState, depth, agent):
            if gameState.isLose() or gameState.isWin():
                return self.evaluationFunction(gameState)
            legalActions = gameState.getLegalActions(agent)
            scores = []
            for action in legalActions:
                if agent == gameState.getNumAgents() - 1:
                    if depth == self.depth - 1:
                        scores.append(self.evaluationFunction(gameState.generateSuccessor(agent, action)))
                    else:
                        scores.append(maxi(gameState.generateSuccessor(agent, action), depth + 1))
                else:
                    scores.append(mini(gameState.generateSuccessor(agent, action), depth, agent + 1))
            return min(scores)

        return maxi(gameState, 0)


class AlphaBetaAgent(MultiAgentSearchAgent):
    """
      Your minimax agent with alpha-beta pruning (question 3)
    """

    def getAction(self, gameState):
        """
          Returns the minimax action using self.depth and self.evaluationFunction
        """
        "*** YOUR CODE HERE ***"

        def maxi(gameState, depth, alpha, beta):
            if gameState.isWin() or gameState.isLose():
                return self.evaluationFunction(gameState)
            legalActions = gameState.getLegalActions()
            scores = []
            for action in legalActions:
                scores.append(mini(gameState.generateSuccessor(0, action), depth, 1, alpha, beta))
                alpha = max(alpha, max(scores))
                if max(scores) > beta:
                    return max(scores)
            bestActions = []
            for i in range(len(legalActions)):
                if scores[i] == max(scores):
                    bestActions.append(legalActions[i])
            if depth == 0:
                return random.choice(bestActions)
            else:
                return max(scores)

        def mini(gameState, depth, agent, alpha, beta):
            if gameState.isLose() or gameState.isWin():
                return self.evaluationFunction(gameState)
            legalActions = gameState.getLegalActions(agent)
            scores = []
            for action in legalActions:
                if agent == gameState.getNumAgents() - 1:
                    if depth == self.depth - 1:
                        scores.append(self.evaluationFunction(gameState.generateSuccessor(agent, action)))
                    else:
                        scores.append(maxi(gameState.generateSuccessor(agent, action), depth + 1, alpha, beta))
                else:
                    scores.append(mini(gameState.generateSuccessor(agent, action), depth, agent + 1, alpha, beta))
                beta = min(beta, min(scores))
                if min(scores) < alpha:
                    return min(scores)
            return min(scores)

        return maxi(gameState, 0, float("-inf"), float("inf"))

        util.raiseNotDefined()

class ExpectimaxAgent(MultiAgentSearchAgent):
    """
      Your expectimax agent (question 4)
    """

    def getAction(self, gameState):
        """
          Returns the expectimax action using self.depth and self.evaluationFunction

          All ghosts should be modeled as choosing uniformly at random from their
          legal moves.
        """
        "*** YOUR CODE HERE ***"
        util.raiseNotDefined()

def betterEvaluationFunction(currentGameState):
    """
      Your extreme ghost-hunting, pellet-nabbing, food-gobbling, unstoppable
      evaluation function (question 5).

      DESCRIPTION: <write something here so we know what you did>
    """
    "*** YOUR CODE HERE ***"
    util.raiseNotDefined()

class RandomAgent(Agent):
    def getAction(self, gameState):
        legalMoves = gameState.getLegalActions()
        # Pick randomly among the legal
        chosenIndex = random.choice(range(0, len(legalMoves)))
        return legalMoves   [chosenIndex]


# Abbreviation
better = betterEvaluationFunction

