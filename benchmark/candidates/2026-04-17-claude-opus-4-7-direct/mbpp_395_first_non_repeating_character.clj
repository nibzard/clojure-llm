(defn first_non_repeating_character
  "Write a cljthon function to find the first non-repeated character in a given string."
  [str1]
  (when (seq str1)
    (let [freq (frequencies str1)
          first-non (first (filter #(= 1 (get freq %)) str1))]
      first-non)))