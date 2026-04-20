(defn check_dict_case
  "Given a map, return true if all keys are strings in lower 
  case or all keys are strings in upper case, else return false.
  The function should return false is the given map is empty."
  [dict]
  (and (map? dict)
       (not (empty? dict))
       (let [keys (keys dict)
             all-strings? (every? string? keys)
             all-lower? (every? #(= % (clojure.string/lower-case %)) keys)
             all-upper? (every? #(= % (clojure.string/upper-case %)) keys)]
         (and all-strings?
              (or all-lower? all-upper?)))))