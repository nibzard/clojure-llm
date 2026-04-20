(defn reverse_vowels
  "	Write a cljthon function to reverse only the vowels of a given string (where y is not a vowel)."
  [str1]
  (let [vowels? (set "aeiouAEIOU")
        chars (vec (or str1 ""))
        vowel-idxs (keep-indexed (fn [i ch] (when (vowels? ch) i)) chars)
        vowel-chars (map chars (reverse vowel-idxs))]
    (apply str
           (reduce (fn [v [i ch]]
                     (assoc v i (if (vowels? ch) (first vowel-chars) ch)))
                   chars
                   (map vector vowel-idxs vowel-chars)))))