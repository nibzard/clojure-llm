(defn first_non_repeating_character
  "Write a cljthon function to find the first non-repeated character in a given string."
  [str1]
  (when (some? str1)
    (let [freqs (frequencies str1)]
      (some (fn [ch]
              (when (= 1 (get freqs ch))
                ch))
            str1))))