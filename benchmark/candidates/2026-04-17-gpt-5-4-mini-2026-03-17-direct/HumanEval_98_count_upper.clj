(defn count_upper
  "Given a string s, count the number of uppercase vowels in even indices.
  For example:
  >>> (count_upper \"aBCdEf\")
  1
  >>> (count_upper \"abcdefg\")
  0
  >>> (count_upper \"dBBE\")
  0"
  [s]
  (let [upper-vowels #{\A \E \I \O \U}]
    (count (filter (fn [[idx ch]]
                    (and (even? idx) (contains? upper-vowels ch)))
                  (map-indexed vector (or s ""))))))