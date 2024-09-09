import tkinter as tk
from tkinter import messagebox
import json
import os
import re

def save_to_json():
    card_name = card_name_entry.get()
    title = title_entry.get()
    text = text_entry.get("1.0", "end-1c")
    image = image_entry.get()
    tags = tags_entry.get().split(',')

    # Find and replace hyperlinks with HTML anchor tags
    link_pattern = r'(https?://\S+)'
    text_with_links = re.sub(link_pattern, r'<a href="\1">\1</a>', text)

    data = {
        "title": title,
        "text": text_with_links,
        "image": image,
        "tags": [tag.strip() for tag in tags]
    }

    output_file = "data.json"

    if os.path.exists(output_file):
        with open(output_file, 'r') as json_file:
            existing_data = json.load(json_file)
    else:
        existing_data = {}

    if card_name in existing_data:
        messagebox.showerror("Error", "Card name already exists in the JSON file.")
        return

    existing_data[card_name] = data

    with open(output_file, 'w') as json_file:
        json.dump(existing_data, json_file, indent=4)

    messagebox.showinfo("Success", "Data appended to " + output_file)

# Create the main window
root = tk.Tk()
root.title("Card Data Entry")

# Create labels and entry fields
card_name_label = tk.Label(root, text="Card Name:")
card_name_label.pack()
card_name_entry = tk.Entry(root)
card_name_entry.pack()

title_label = tk.Label(root, text="Title:")
title_label.pack()
title_entry = tk.Entry(root)
title_entry.pack()

text_label = tk.Label(root, text="Text:")
text_label.pack()
text_entry = tk.Text(root, height=5, width=40)
text_entry.pack()

image_label = tk.Label(root, text="Image Address:")
image_label.pack()
image_entry = tk.Entry(root)
image_entry.pack()

tags_label = tk.Label(root, text="Tags (comma-separated):")
tags_label.pack()
tags_entry = tk.Entry(root)
tags_entry.pack()

# Create a save button
save_button = tk.Button(root, text="Save to JSON", command=save_to_json)
save_button.pack()

# Start the GUI event loop
root.mainloop()
