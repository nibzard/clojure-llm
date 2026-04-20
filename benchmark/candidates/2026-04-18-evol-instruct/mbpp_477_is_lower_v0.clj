(defn palindromic?
  "Return true if the given string reads the same forwards and backwards,
  ignoring case and any non-letter characters.

  Examples:
  (palindromic? \"A man, a plan, a canal: Panama\") => true
  (palindromic? \"Hello, world!\") => false
  (palindromic? nil) => false"
  [s]
  (if (string? s)
    (let [cleaned (->> s
                       (filter #(Character/isLetter ^char %))
                       (map #(Character/toLowerCase ^char %))
                       (apply str))]
      (= cleaned (clojure.string/reverse cleaned)))
    false))