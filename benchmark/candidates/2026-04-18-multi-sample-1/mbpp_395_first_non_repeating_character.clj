(defn first_non_repeating_character
  "	Write a cljthon function to find the first non-repeated character in a given string."
  [str1]
  (when (seq str1)
    (let [freqs (frequencies str1)]
      (some #(when (= 1 (get freqs %)) %) str1))))