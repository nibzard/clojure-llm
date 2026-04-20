(defn contains-in-order?
  "Return true if all items in `needle` appear in `haystack` in the same order as a subsequence.
  Items do not need to be contiguous.

  Examples:
  (contains-in-order? [1 3] [1 2 3 4]) ;=> true
  (contains-in-order? [:a :c] [:a :b :c]) ;=> true
  (contains-in-order? [2 1] [1 2 3]) ;=> false
  (contains-in-order? [] [1 2 3]) ;=> true"
  [needle haystack])