(defn remove_odd
  "	Write a function to remove odd characters in a string."
  [str1]
  (apply str (take-nth 2 str1)))