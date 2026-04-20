(defn extract_string
  "	Write a function to extract specified size of strings from a given list of string values."
  [str l]
  (let [n (if (string? str) (count str) (or str 0))]
    (filter #(= (count %) n) l)))