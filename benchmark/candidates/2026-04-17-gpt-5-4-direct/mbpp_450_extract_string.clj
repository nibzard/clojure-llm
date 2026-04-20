(defn extract_string
  "Write a function to extract specified size of strings from a given list of string values."
  [str l]
  (let [n (cond
            (number? str) str
            (string? str) (count str)
            :else 0)]
    (->> l
         (filter string?)
         (filter #(= (count %) n))
         seq)))