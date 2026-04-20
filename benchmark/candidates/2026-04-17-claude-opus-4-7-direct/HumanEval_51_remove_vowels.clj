(defn remove_vowels
  "remove_vowels is a function that takes string and returns string without vowels."
  [text]
  (clojure.string/replace text #"[aeiouAEIOU]" ""))