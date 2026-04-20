(defn extract_string
  "	Write a function to extract specified size of strings from a given list of string values."
  [str l]
  (let [n (if (string? str)
            (count str)
            (long str))]
    (map #(subs % 0 (min n (count %))) l)))