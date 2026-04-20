(defn middle-sum
  "Return the sum of the two middle values after sorting a collection of numbers.
  If the collection has an odd count, use the same middle value twice.
  Return nil for an empty collection.

  Examples:
  (middle-sum [5 1 9 3]) => 8   ; sorted: [1 3 5 9], middle values 3 and 5
  (middle-sum [7 2 10]) => 14   ; sorted: [2 7 10], middle value 7 used twice
  (middle-sum []) => nil"
  [nums]
  (when (seq nums)
    (let [s (sort nums)
          n (count s)
          mid (quot n 2)]
      (if (odd? n)
        (* 2 (nth s mid))
        (+ (nth s (dec mid)) (nth s mid))))))