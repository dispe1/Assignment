import tensorflow as tf
import os
from glob import glob
from PIL import Image
import dlib
import numpy as np

def get_label_from_path(path):
    return path.split('\\')[-2]

def read_image(path):
    image = np.array(Image.open(path))
    return image.reshape(image.shape[0], image.shape[1], image.shape[2])

def onehot_encode_label(path,unique_label_names):
    onehot_label = unique_label_names == get_label_from_path(path)
    onehot_label = onehot_label.astype(np.uint8)
    return onehot_label

def _read_py_function(path, label):
    image = read_image(path)
    label = np.array(label, dtype=np.uint8)
    return image.astype(np.int32), label

def _read_test_py_function(path):
    predictor_path = 'shape_predictor_68_face_landmarks.dat'
    face_rec_model_path = 'dlib_face_recognition_resnet_model_v1.dat'

    detector = dlib.get_frontal_face_detector()
    sp = dlib.shape_predictor(predictor_path)
    facerec = dlib.face_recognition_model_v1(face_rec_model_path)

    img = dlib.load_rgb_image(path)
    dets = detector(img, 1)
    if len(dets) >= 1:
        d = dets[0]
        shape = sp(img, d)
        image = dlib.get_face_chip(img, shape, size=128, padding=0.1)
        return image.astype(np.int32)
    else:
        return img.astype(np.int32);

def _resize_function(image_decoded, label):
    image_decoded.set_shape([None, None, None])
    image_resized = tf.image.resize_images(image_decoded, [128, 128])
    return image_resized, label

def _resize_test_function(image_decoded):
    image_decoded.set_shape([None, None, None])
    image_resized = tf.image.resize_images(image_decoded, [128, 128])
    return image_resized


def read_test_data(dir):
    data_list = glob(dir + '\\*.jpg')

    dataset = tf.data.Dataset.from_tensor_slices(data_list)
    dataset = dataset.map(
        lambda data_list: tuple(tf.py_func(_read_test_py_function, [data_list], [tf.int32])))
    dataset = dataset.map(_resize_test_function)
    dataset = dataset.repeat()
    dataset = dataset.batch(1)

    return dataset, data_list

def read_data(batch_size, dir):

    data_list = glob(dir + '\\*\\*.jpg')
    label_name_list = []

    for path in data_list:
        label_name_list.append(get_label_from_path(path))
    unique_label_names = np.unique(label_name_list)

    label_list = [onehot_encode_label(path,unique_label_names).tolist() for path in data_list]

    dataset = tf.data.Dataset.from_tensor_slices((data_list, label_list))
    dataset = dataset.map(
        lambda data_list, label_list: tuple(tf.py_func(_read_py_function, [data_list, label_list], [tf.int32, tf.uint8])))

    dataset = dataset.map(_resize_function)
    dataset = dataset.repeat()
    dataset = dataset.shuffle(buffer_size=(int(len(data_list) * 0.3) + 3 * batch_size))
    dataset = dataset.batch(batch_size)

    return dataset, len(data_list)
