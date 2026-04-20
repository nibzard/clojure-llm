(defn smallest_palindrome_edit-distance
  "Given a collection coll, return the minimum number of element changes needed
  to make it palindromic. A collection is palindromic if it reads the same
  forwards and backwards.

  The input may be any sequential collection, including lists and lazy sequences.
  Nil counts as an empty collection.

  Examples:
  >>> (smallest_palindrome_edit-distance [1 2 3 5 4 7 9 6])
  4
  >>> (smallest_palindrome-edit-distance '(1 2 3 4 3 2 2))
  1
  >>> (smallest_palindrome-edit-distance nil)
  0
  >>> (smallest_palindrome-edit-distance (range 5))
  2"
  [coll])