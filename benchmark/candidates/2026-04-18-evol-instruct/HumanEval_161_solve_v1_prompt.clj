(defn solve
  "You are given a vector v.
  For each element:
  - if it is a number, double it
  - if it is a keyword, keep it as-is
  - if it is nil, replace it with :missing
  - otherwise, leave it unchanged
  Return a new vector with the transformed elements.

  Examples
  >>> (solve [1 :a nil \"x\" 2])
  [2 :a :missing \"x\" 4]

  >>> (solve [])
  []

  >>> (solve [nil nil])
  [:missing :missing]"
  [v])