(defn total_match
  "Write a function that accepts two vectors of any values and returns the vector with the smaller total count of items after flattening one level.
  Count only the top-level items if the element is not sequential, but if an element is a sequential collection, count its items instead.
  If the two vectors have the same total count, return the first vector.

  Examples:
  >>> (total_match [] [])
  []
  >>> (total_match [1 [2 3] 4] [[5] 6])
  [[5] 6]
  >>> (total_match [[1 2] [3]] [4 5 6])
  [[1 2] [3]]
  >>> (total_match [[:a] :b :c] [[:x y]])
  [[:x y]]

  Note: nil counts as 0 and should not cause an error."
  [v1 v2]
  (letfn [(score [v]
            (reduce
              (fn [acc x]
                (+ acc (if (sequential? x) (count x) 1)))
              0
              (or v [])))]
    (if (<= (score v1) (score v2))
      v1
      v2)))