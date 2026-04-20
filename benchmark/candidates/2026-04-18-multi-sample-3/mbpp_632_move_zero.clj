(defn move_zero
  "	Write a cljthon function to move all zeroes to the end of the given list."
  [num_list]
  (let [xs (or num_list [])
        non-zeroes (remove zero? xs)
        zeroes (filter zero? xs)]
    (concat non-zeroes zeroes)))