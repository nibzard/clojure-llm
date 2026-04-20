(defn split-at-nil
  "Given a sequence of pairs, return a vector of two vectors:
   - the first contains the first elements of each pair
   - the second contains the second elements of each pair

   Stop processing when a nil pair is encountered.

   Examples:
   (split-at-nil [[1 2] [3 4] nil [5 6]])
   => [[1 3] [2 4]]

   (split-at-nil [])
   => [[] []]"
  [pairs]
  (let [pairs (take-while some? pairs)]
    [(mapv first pairs)
     (mapv second pairs)]))