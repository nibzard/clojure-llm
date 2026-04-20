(defn normalize-key-case
  "Given a map, return :lower if all string keys are lowercase, :upper if all string keys are uppercase, :mixed if string keys use both cases, and :invalid if any key is not a string. Return :empty for an empty map.

  Examples:
  >>> (normalize-key-case {\"a\" 1, \"b\" 2})
  :lower
  >>> (normalize-key-case {\"A\" 1, \"B\" 2})
  :upper
  >>> (normalize-key-case {\"a\" 1, \"B\" 2})
  :mixed
  >>> (normalize-key-case {\"a\" 1, 8 2})
  :invalid
  >>> (normalize-key-case {})
  :empty"
  [m]
  (cond
    (empty? m) :empty
    (some #(not (string? %)) (keys m)) :invalid
    (let [ks (mapcat seq (keys m))]
      (and (some #(Character/isLowerCase ^char %) ks)
           (some #(Character/isUpperCase ^char %) ks))) :mixed
    (every? #(Character/isLowerCase ^char %) (mapcat seq (keys m))) :lower
    (every? #(Character/isUpperCase ^char %) (mapcat seq (keys m))) :upper
    :else :mixed))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= :lower (normalize-key-case {"a" 1, "b" 2})))
  (is (= :upper (normalize-key-case {"A" 1, "B" 2})))
  (is (= :mixed (normalize-key-case {"a" 1, "B" 2})))
  (is (= :invalid (normalize-key-case {"a" 1, 8 2})))
  (is (= :empty (normalize-key-case {}))))

(run-test test-variation)
