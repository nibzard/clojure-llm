(defn get_closest_vowel
  "You are given a word. Your task is to find the closest vowel that stands between 
  two consonants from the right side of the word (case sensitive).
  Vowels in the beginning and ending doesn't count. Return empty string if you didn't
  find any vowel met the above condition. 
  You may assume that the given string contains English letter only.
  Example:
  >>> (get_closest_vowel \"yogurt\")
  \"u\"
  >>> (get_closest_vowel \"FULL\")
  \"U\"
  >>> (get_closest_vowel \"quick\")
  \"\"
  >>> (get_closest_vowel \"ab\")
  \"\""
  [word]
  (let [vowels #{\a \e \i \o \u \A \E \I \O \U}
        consonant? (fn [ch] (and ch (not (vowels ch))))
        n (count word)]
    (or
      (some (fn [i]
              (let [c (nth word i)
                    l (nth word (dec i))
                    r (nth word (inc i))]
                (when (and (vowels c)
                           (consonant? l)
                           (consonant? r))
                  (str c))))
            (range (- n 2) 0 -1))
      "")))