(defn balanced-frequencies
  "Return true if the two collections contain the same multiset of elements, ignoring order.

  Examples:
  (balanced-frequencies [1 2 2 3] '(2 3 2 1)) => true
  (balanced-frequencies [:a nil :b] [:b :a nil]) => true
  (balanced-frequencies [1 2 3] [1 2 2]) => false"
  [xs ys]
  (= (frequencies xs) (frequencies ys)))