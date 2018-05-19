def binary_search(target, arr):
    length = len(arr)
    if length == 1 and arr[0] != target:
        return False
    elif arr[length // 2] == target:
        return True
    elif arr[length // 2] > target:
        return binary_search(target, arr[:length // 2])
    else:
        return binary_search(target, arr[length // 2:])
