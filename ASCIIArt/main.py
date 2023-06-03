#Author: Aria Ali
#Description: This program takes an image and converts it to its ASCII representation.
from PIL import Image, ImageDraw, ImageFont

def rgb_to_gray(rgb):  # rgb -> grayscale conversion
    return sum(rgb) // 3

ASCII_CHARS = '@%#*+=-:.g '

image = Image.open('knight.jpg').convert('RGB')  # open/convert image
new_width = 100  # width

aspect_ratio = image.height / image.width
new_height = int(new_width * aspect_ratio)  # new height

image = image.resize((new_width, new_height))  # resize
pixels = image.load()

gray_px = [[rgb_to_gray(pixels[x, y]) for x in range(new_width)] for y in range(new_height)]  # pixel -> grayscale conversion

ascii_image = Image.new('RGB', (new_width * 11, new_height * 11), color='white')  # new image
draw = ImageDraw.Draw(ascii_image)  # drawing object instantiation

font = ImageFont.truetype(r"C:\Users\mitte\Desktop\fonts\IBMPlexMono-Regular.ttf", 11)  # load font (change path for your font location)

contrast = {0: ASCII_CHARS[0], 255: ASCII_CHARS[-1]}  # grayscale mapped to ascii

num_chars = len(ASCII_CHARS) - 2
for i in range(1, num_chars + 1):  # adjust contrast mapping
    gray_value = i * 255 // num_chars
    ascii_char = ASCII_CHARS[i]
    contrast[gray_value] = ascii_char

for y in range(new_height):  # iterate over each pixel
    for x in range(new_width):
        gray_value = gray_px[y][x]
        ascii_char = contrast.get(gray_value, ASCII_CHARS[gray_value * num_chars // 255 + 1])  # map grayscale val per ascii char
        draw.text((x * 11, y * 11), ascii_char, font=font, fill='black')  # draw ASCII character on the image

ascii_image.save('ascii_image.png')  # save the image