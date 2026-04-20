(defn maximum-non-nil
  "Given a collection coll and a positive integer k, return a sorted vector of the
  maximum k non-nil values in coll.

  Differences from the original:
  - coll may be any sequence type, including lazy or infinite sequences.
  - nil values must be ignored.
  - If k is 0, return [].
  - If there are fewer than k non-nil values, return all of them sorted.

  Examples:
  >>> (maximum-non-nil [3 nil 1 2] 2)
  [2 3]
  >>> (maximum-non-nil '(nil 5 4 nil 1) 3)
  [1 4 5]
  >>> (maximum-non-nil (concat [2 nil] (repeat 1)) 4)
  [1 1 1 2]"
  [coll k])