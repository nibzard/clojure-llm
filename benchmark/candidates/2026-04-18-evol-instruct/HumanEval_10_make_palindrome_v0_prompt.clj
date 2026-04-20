(defn make_palindrome
  "Return the shortest palindrome vector that begins with the supplied vector.

  The input is a vector of any values, not a string. The result must be the
  shortest palindrome vector that starts with the given vector by appending the
  minimal number of elements to the end.

  Examples:
  >>> (make_palindrome [])
  []
  >>> (make_palindrome [:a :b :c])
  [:a :b :c :b :a]
  >>> (make_palindrome [1 2 3 2])
  [1 2 3 2 1]

  The function should work for vectors of any comparable Clojure values."
  [v])