(defn same_elements
  "Check if two collections contain the same unique elements, ignoring order and duplicates.
  The collections may be vectors, lists, or sets, and either argument may be nil.

  >>> (same_elements [1 2 2 3] '(3 1 1 2))
  true
  >>> (same_elements #{:a :b} [:b :a :a])
  true
  >>> (same_elements nil [])
  true
  >>> (same_elements [1 2 3] [1 2 4])
  false
  >>> (same_elements [1 2] nil)
  false"
  [c0 c1])