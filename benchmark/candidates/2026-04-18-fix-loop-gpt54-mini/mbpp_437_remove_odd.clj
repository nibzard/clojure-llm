(defn remove_odd
  "	Write a function to remove odd characters in a string."
  [str1]
  (apply str (map second (partition 2 2 nil str1))))