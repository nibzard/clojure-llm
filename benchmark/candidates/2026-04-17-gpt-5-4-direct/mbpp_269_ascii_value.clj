(defn ascii_value
  "	Write a function to find the ascii value of a character."
  [k]
  (when (some? k)
    (int (if (char? k) k (first (str k))))))