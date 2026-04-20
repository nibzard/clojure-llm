(defn _sum
  "Write a cljthon function to find the sum of a vector."
  [arr]
  (if (vector? arr)
    (reduce + 0 arr)
    (throw (IllegalArgumentException. "Input must be a vector"))))