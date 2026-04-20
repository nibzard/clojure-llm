(defn deep-lower
  "Recursively convert all string values in a nested map/vector structure to lower case.

  Examples:
  (deep-lower {:a \"HELLO\" :b [\"WORLD\" {:c \"ClJ\"}]})
  => {:a \"hello\", :b [\"world\" {:c \"clj\"}]}

  Non-string values are left unchanged. Nil is returned as nil."
  [x]
  (cond
    (nil? x) nil
    (string? x) (.toLowerCase ^String x)
    (map? x) (into (empty x) (map (fn [[k v]] [k (deep-lower v)]) x))
    (vector? x) (vec (map deep-lower x))
    (sequential? x) (doall (map deep-lower x))
    :else x))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a "hello" :b ["world" {:c "clj"}] :d 42}
         (deep-lower {:a "HELLO" :b ["WORLD" {:c "ClJ"}] :d 42}))))

(run-test test-variation)
