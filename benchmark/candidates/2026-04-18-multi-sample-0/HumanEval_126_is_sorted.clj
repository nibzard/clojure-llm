(defn is_sorted
  "Given a list of numbers, return whether or not they are sorted
  in ascending order. If list has more than 1 duplicate of the same
  number, return false. Assume no negative numbers and only integers.
  Examples
  >>> (is_sorted [5])
  true
  >>> (is_sorted [1 2 3 4 5])
  true
  >>> (is_sorted [1 3 2 4 5])
  false
  >>> (is_sorted [1 2 3 4 5 6])
  true
  >>> (is_sorted [1 2 3 4 5 6 7])
  true
  >>> (is_sorted [1 3 2 4 5 6 7])
  false
  >>> (is_sorted [1 2 2 3 3 4])
  true
  >>> (is_sorted [1 2 2 2 3 4])
  false"
  [lst]
  (or
   (empty? lst)
   (every?
    true?
    (map (fn [[a b c]]
           (and (<= a b)
                (or (nil? c) (not= a b c))))
         (partition-all 3 1 lst)))))