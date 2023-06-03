#Author: Aria Ali
#Description: This program calculates and outputs a visual representation of
#the probability of rolling dice.
import numpy as np
import matplotlib.pyplot as plt

# Probability of rolling item drop per roll
p = 1/512

# Number of trials
n = 1000

# Calculate the probability of getting 3 or more successful rolls
prob = sum([np.math.comb(n, i) * p**i * (1-p)**(n-i) for i in range(3, n+1)])

# Print the result
print(f"The percent chance of obtaining at least 3 successes in 1000 rolls is {prob*100:.2f}%")

# Generate the distribution graph
x = np.arange(6)
y = [np.math.comb(n, i) * p**i * (1-p)**(n-i) for i in x]
plt.bar(x, y)
plt.title("Distribution of Rolls")
plt.xlabel("Number of Successful Rolls")
plt.ylabel("Probability")
plt.show()
