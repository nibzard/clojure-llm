(defn extract_string
  "	Write a function to extract specified size of strings from a given list of string values."
  [str l]
  (let [n (if (number? str) str (try (Long/parseLong (str str)) (catch Exception _ nil)))]
    (if (and n (sequential? l))
      (filter #(= (count %) n) (filter string? l))
      '())))