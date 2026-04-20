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
    (reduce (fn [best w]
              (let [best-uniq (count (set best))
                    w-uniq    (count (set w))]
                (cond
                  (> w-uniq best-uniq) w
                  (< w-uniq best-uniq) best
                  (neg? (compare w best)) w
                  :else best)))
            words)))