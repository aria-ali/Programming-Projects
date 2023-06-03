#Author: Aria Ali
#Description: This program calculates and outputs a visual representation of
#the inputs required to perform a driving technique known as 'Mini-Turbo'
#for the game 'Mario Kart 64'

import time
import matplotlib.pyplot as plt
import matplotlib.patches as patches
import os
from PIL import Image
import moviepy.editor as mpy
points = [(65,0), (65,0), (65,0), (20,0), (20,0), (65,0), (-65,0), (-65,0), (-65,0), (-60,0), (-60,0), (-60,0), (-60,0), (-60,0), (-60,0), (39,0), (-50,0), (-50,0), (-50,0), (-50,0), (40,0), (-55,0), (-65,0), (-65,0), (-65,0), (-65,0), (-65,0), (-65,0), (-65,0), (-65,0), (-65,0), (-65,0), (65,0), (0,0)]
num_points = len(points)
delay = 1 / 30  # Delay between frames (30fps)

# Initialize the graph
fig, ax = plt.subplots()
ax.set_xlim(-80, 80)  # Set the x-axis limits
ax.set_ylim(-10, 10)  # Set the y-axis limits
ax.set_xlabel('x')
ax.set_ylabel('y')
ax.set_title('Points on a Graph')

# Add octagon in the background
octagon = patches.RegularPolygon((0, 0), numVertices=8, radius=70, fill=False, edgecolor='gray')
ax.add_patch(octagon)

frames_dir = 'frames'
os.makedirs(frames_dir, exist_ok=True)

for i in range(num_points):
    point = points[i]
    x, y = point

    if i < 16:
        color = 'gray'
    elif 16 <= i < 21:
        color = 'yellow'
    else:
        color = 'orange'

    ax.plot(x, y, 'o', markersize=10, color=color)  # Plot the point with the specified color

    # Display the frame number in the top right corner
    ax.text(0.95, 0.95, f'Frame {i+1}', horizontalalignment='right', verticalalignment='top', transform=ax.transAxes)

    # Save the current frame as a JPEG file
    frame_filename = os.path.join(frames_dir, f'frame_{i+1:04d}.jpg')
    plt.savefig(frame_filename)

    # Clear the plot
    ax.cla()
    ax.set_xlim(-80, 80)  # Set the x-axis limits
    ax.set_ylim(-10, 10)  # Set the y-axis limits
    ax.set_xlabel('x')
    ax.set_ylabel('y')
    ax.set_title('Points on a Graph')

    # Add octagon in the background
    ax.add_patch(octagon)

# Convert the frames to a GIF
images = []
for i in range(num_points):
    frame_filename = os.path.join(frames_dir, f'frame_{i+1:04d}.jpg')
    images.append(Image.open(frame_filename))

gif_filename = 'animation.gif'
images[0].save(gif_filename, format='GIF', append_images=images[1:], save_all=True, duration=int(delay*1000), loop=0)

# Delete the frame images
for i in range(num_points):
    frame_filename = os.path.join(frames_dir, f'frame_{i+1:04d}.jpg')
    os.remove

frames_dir = 'frames'
gif_filename = 'animation.gif'

# Load the GIF frames
gif = mpy.ImageSequenceClip(sorted(os.listdir(frames_dir)), fps=30)
