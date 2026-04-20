(defn make_palindrome_vec
  "Return the shortest palindrome vector that begins with the supplied vector.

  The input is a vector of any values, not a string. Preserve element equality
  and return a vector.

  Examples:
  >>> (make_palindrome_vec [])
  []
  >>> (make_palindrome_vec [:a :b :c])
  [:a :b :c :a]
  >>> (make_palindrome_vec [1 2 1])
  [1 2 1]
  >>> (make_palindrome_vec [1 2 3 4])
  [1 2 3 4 3 2 1]"
  [v])