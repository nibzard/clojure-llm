(defn find_max
  "Write a function that accepts a list of strings.
  The list contains different words. Return the word with maximum number
  of unique characters. If multiple strings have maximum number of unique
  characters, return the one which comes first in lexicographical order.
  >>> (find_max [\"name\" \"of\" \"string\"])
  \"string\"
  >>> (find_max [\"name\" \"enam\" \"game\"])
  \"enam\"
  >>> (find_max [\"aaaaaaa\" \"bb\" \"cc\"])
  \"aaaaaaa\""
  [words]
  (when (seq words)
    (let [counts (map (fn [w] [(count (set w)) w]) words)
          max-count (apply max (map first counts))
          candidates (map second (filter #(= max-count (first %)) counts))]
      (apply min candidates))))