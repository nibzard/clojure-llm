(defn remove_odd
  "	Write a function to remove odd characters in a string."
  [str1]
  (apply str (keep-indexed (fn [idx ch] (when (even? idx) ch)) (or str1 ""))))