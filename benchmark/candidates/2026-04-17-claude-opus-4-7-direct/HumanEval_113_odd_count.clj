(defn odd_count
  "Given a list of strings, where each string consists of only digits, return a list.
  Each element i of the output should be \"the number of odd elements in the
  string i of the input.\" where all the i's should be replaced by the number
  of odd digits in the i'th string of the input."
  [lst]
  (let [template "the number of odd elements in the string i of the input."]
    (map (fn [s]
           (let [cnt (count (filter #(odd? (Character/digit % 10)) s))]
             (clojure.string/replace template #"i" (str cnt))))
         lst)))