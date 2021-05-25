"""
TODO install
Pillow

"""

def reformat_image(src_image, dst_image):

    from PIL import Image

    basewidth = 50
    baseheight = 50
    img = Image.open(src_image)
    wpercent = (basewidth / float(img.size[0]))
    hsize = int((float(img.size[1]) * float(wpercent)))
    img = img.resize((basewidth, baseheight), Image.ANTIALIAS)
    img.save(dst_image)

def walk_dir():
    import os
    for path, subdirs, files in os.walk("resized_new_imgs\\"):
        for name in files:
            print(os.path.join(path, name))

def resize_all_images_in(p):
    import os
    for path, subdirs, files in os.walk(p):
        for name in files:
            f_p = os.path.join(path, name)
            # print(os.path.join(path, name))
            print(f_p)
            reformat_image(f_p, f_p)


def main():
    # walk_dir()

    resize_all_images_in("resized_new_imgs\\")
    # reformat_image("playAgain.png", "playAgain.png")


if __name__ == '__main__':
    main()

