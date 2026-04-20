(defn get_closest_vowel
  "You are given a word. Your task is to find the closest vowel that stands between 
  two consonants from the right side of the word (case sensitive).
  Vowels in the beginning and ending doesn't count. Return empty string if you didn't
  find any vowel met the above condition. 
  You may assume that the given string contains English letter only."
  [word]
  (let [vowels #{"a" "e" "i" "o" "u" "A" "E" "I" "O" "U"}
        consonants? (fn [c] (and (Character/isLetter c) 
                                (not (contains? vowels (str c)))))
        chars (vec word)
        n (count chars)]
    (loop [i (dec n)]
      (when (and (> i 0) 
                 (< i (dec n))
                 (contains? vowels (str (chars i)))
                 (consonants? (chars (dec i)))
                 (consonants? (chars (inc i))))
        (str (chars i))))))