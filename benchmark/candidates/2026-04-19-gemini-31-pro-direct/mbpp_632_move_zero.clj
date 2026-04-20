(defn move_zero
  "Write a cljthon function to move all zeroes to the end of the given list."
  [num_list]
  (concat (remove zero? num_list)
          (filter zero? num_list)))