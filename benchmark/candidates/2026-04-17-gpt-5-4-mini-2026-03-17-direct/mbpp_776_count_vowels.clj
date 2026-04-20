(defn count_vowels
  "	Write a function to count those characters which have vowels as their neighbors in the given string."
  [test_str]
  (let [vowels? (set "aeiouAEIOU")
        chars (seq (or test_str ""))]
    (count
      (filter true?
              (map (fn [[a b c]]
                     (and (vowels? a) (vowels? c)))
                   (partition 3 1 chars)))))