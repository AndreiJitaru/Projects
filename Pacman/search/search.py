# search.py
# ---------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to http://ai.berkeley.edu.
# 
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


"""
In search.py, you will implement generic search algorithms which are called by
Pacman agents (in searchAgents.py).
"""

import util
import random
import searchAgents

class SearchProblem:
    """
    This class outlines the structure of a search problem, but doesn't implement
    any of the methods (in object-oriented terminology: an abstract class).

    You do not need to change anything in this class, ever.
    """

    def getStartState(self):
        """
        Returns the start state for the search problem.
        """
        util.raiseNotDefined()

    def isGoalState(self, state):
        """
          state: Search state

        Returns True if and only if the state is a valid goal state.
        """
        util.raiseNotDefined()

    def getSuccessors(self, state):
        """
          state: Search state

        For a given state, this should return a list of triples, (successor,
        action, stepCost), where 'successor' is a successor to the current
        state, 'action' is the action required to get there, and 'stepCost' is
        the incremental cost of expanding to that successor.
        """
        util.raiseNotDefined()

    def getCostOfActions(self, actions):
        """
         actions: A list of actions to take

        This method returns the total cost of a particular sequence of actions.
        The sequence must be composed of legal moves.
        """
        util.raiseNotDefined()


class CustomNode:

    def __init__(self, parent, state, action, path_cost, heuristic, total):
        self.parent = parent
        self.state = state
        self.action = action
        self.path_cost = path_cost
        self.heuristic = heuristic
        self.total = total

    def setParent(self, parent):
        self.parent = parent

    def getParent(self):
        return self.parent

    def getState(self):
        return self.state

    def getAction(self):
        return self.action

    def getPathCost(self):
        return self.path_cost

    def getHeuristic(self):
        return self.euristic

    def getTotal(self):
        return self.total

def tinyMazeSearch(problem):
    """
    Returns a sequence of moves that solves tinyMaze.  For any other maze, the
    sequence of moves will be incorrect, so only use this for tinyMaze.
    """
    from game import Directions
    s = Directions.SOUTH
    w = Directions.WEST
    return  [s, s, w, s, w, w, s, w]

def randomSearch(problem):
	currentState = problem.getStartState()
	stepsToTake = []
	while not problem.isGoalState(currentState):
		successors = problem.getSuccessors(currentState)
		whereTo = random.choice(successors)
		currentState = whereTo[0]
		stepsToTake.append(whereTo[1])
	return stepsToTake

# functia solution o ia de la nodul solutie pana la nodul start memorand actiunile
def solution(node):
    res = []
    if node.getParent():
        res.append(node.getAction())
        res = res + solution(node.getParent())
    return res

def depthFirstSearch(problem):

    current_node = CustomNode(None, problem.getStartState(), None, 0, 0, 0) # am creat nodul de start

    if problem.isGoalState(current_node.getState()): # daca nodul de start este solutia
        return list(reversed(solution(current_node)))

    frontier = util.Stack()  # coada pentru bfs
    frontier.push(current_node)

    explored = []  # lista in care memoram nodurile vizitate
    explored.append(current_node.getState())

    while not frontier.isEmpty():
        current_node = frontier.pop() # luam nodul curent din coada
        if problem.isGoalState(current_node.getState()):
            return list(reversed(solution(current_node)))
        successors = problem.getSuccessors(current_node.getState())
        for i in successors:
            child = CustomNode(current_node, i[0], i[1], i[2], 0, 0)
            if child.getState() not in explored:
                frontier.push(child)
        explored.append(current_node.getState())

    return []

    util.raiseNotDefined()

def breadthFirstSearch(problem):

    start_node = CustomNode(None, problem.getStartState(), None, 0, 0, 0) # am creat nodul de start

    if problem.isGoalState(start_node.getState()): # daca nodul de start este solutia
        return list(reversed(solution(start_node)))

    frontier = util.Queue()  # coada pentru bfs
    frontier.push(start_node)

    explored = []  # lista in care memoram nodurile vizitate
    explored.append(start_node.getState())

    while not frontier.isEmpty():
        current_node = frontier.pop() # luam nodul curent din coada
        if problem.isGoalState(current_node.getState()):
            return list(reversed(solution(current_node)))
        successors = problem.getSuccessors(current_node.getState())
        for i in successors:
            child = CustomNode(current_node, i[0], i[1], i[2], 0, 0)
            if child.getState() not in explored:
                explored.append(child.getState())
                frontier.push(child)
    util.raiseNotDefined()

def uniformCostSearch(problem):
    start_node = CustomNode(None, problem.getStartState(), None, 0, 0, 0)  # am creat nodul de start

    frontier = util.PriorityQueue()  # coada pentru bfs
    frontier.push(start_node, 0)

    explored = []  # lista in care memoram nodurile vizitate
    # explored.append(start_node.getState())

    while not frontier.isEmpty():
        current_node = frontier.pop()
        if problem.isGoalState(current_node.getState()):
            return list(reversed(solution(current_node)))
        explored.append(current_node.getState())
        successors = problem.getSuccessors(current_node.getState())
        for i in successors:
            child = CustomNode(current_node, i[0], i[1], current_node.getPathCost() + i[2], 0, 0)
            if child.getState() not in explored:
                frontier.update(child, child.getPathCost())

    return []

    util.raiseNotDefined()

def nullHeuristic(state, problem=None):
    """
    A heuristic function estimates the cost from the current state to the nearest
    goal in the provided SearchProblem.  This heuristic is trivial.
    """
    return 0

def aStarSearch(problem, heuristic=nullHeuristic):

    #parinte, stare, actiune, cost, euristica, suma
    start_node = CustomNode(None, problem.getStartState(), None, 0, 0, 0)
    frontier = util.PriorityQueue()  # coada pentru bfs
    frontier.push(start_node, 0)

    explored = []

    while not frontier.isEmpty():
        current_node = frontier.pop()
        if problem.isGoalState(current_node.getState()):
            return list(reversed(solution(current_node)))
        explored.append(current_node.getState())
        successors = problem.getSuccessors(current_node.getState())
        for i in successors:
            path_cost = i[2] + current_node.getPathCost()
            euristic = heuristic(i[0], problem)
            child = CustomNode(current_node, i[0], i[1], path_cost, euristic, path_cost + euristic)
            if child.getState() not in explored:
                frontier.update(child, child.getTotal())
    return []

    util.raiseNotDefined()


# Abbreviations
bfs = breadthFirstSearch
dfs = depthFirstSearch
astar = aStarSearch
ucs = uniformCostSearch
