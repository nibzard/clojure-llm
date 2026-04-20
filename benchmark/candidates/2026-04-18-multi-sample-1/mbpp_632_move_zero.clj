(defn move_zero
  "	Write a cljthon function to move all zeroes to the end of the given list."
  [num_list]
  (let [xs (or num_list [])
        non-zeroes (remove zero? xs)
        zero-count (- (count xs) (count non-zeroes))]
    (concat non-zeroes (repeat zero-count 0))))