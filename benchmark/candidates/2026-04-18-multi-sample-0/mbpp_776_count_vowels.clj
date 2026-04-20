(defn count_vowels
  "	Write a function to count those characters which have vowels as their neighbors in the given string."
  [test_str]
  (let [s (or test_str "")
        vowels? (set "aeiouAEIOU")]
    (if (< (count s) 3)
      0
      (count
       (for [i (range 1 (dec (count s)))
             :when (and (vowels? (nth s (dec i)))
                        (vowels? (nth s (inc i))))]
         (nth s i))))))