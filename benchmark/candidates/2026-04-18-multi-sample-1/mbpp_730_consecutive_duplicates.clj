(defn consecutive_duplicates
  "	Write a function to remove consecutive duplicates of a given list."
  [nums]
  (when (seq nums)
    (map first (partition-by identity nums))))