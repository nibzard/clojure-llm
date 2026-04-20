(defn count_vowels
  "	Write a function to count those characters which have vowels as their neighbors in the given string."
  [test_str]
  (let [s (or test_str "")
        vowels? (set "aeiouAEIOU")
        chars (vec s)
        n (count chars)]
    (if (< n 3)
      0
      (count
       (for [i (range 1 (dec n))
             :when (and (vowels? (chars (dec i)))
                        (vowels? (chars (inc i))))]
         (chars i))))))