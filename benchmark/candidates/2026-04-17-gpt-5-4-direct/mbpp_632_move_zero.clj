(defn move_zero
  "	Write a cljthon function to move all zeroes to the end of the given list."
  [num_list]
  (let [xs (or num_list [])
        non-zeros (remove zero? xs)
        zero-count (count (filter zero? xs))]
    (concat non-zeros (repeat zero-count 0))))