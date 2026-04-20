(defn normalize-gaps
  "Given a vector of words and gap markers, return a new vector where:
   - single nil gaps become :_
   - two consecutive nils become :__
   - three or more consecutive nils become :---

   Non-nil values should be preserved in order.

   Examples:
   >>> (normalize-gaps [\"a\" nil \"b\"])
   [\"a\" :_ \"b\"]
   >>> (normalize-gaps [\"a\" nil nil \"b\"])
   [\"a\" :__ \"b\"]
   >>> (normalize-gaps [\"a\" nil nil nil \"b\"])
   [\"a\" :--- \"b\"]
   >>> (normalize-gaps [nil nil \"x\" nil])
   [:__ \"x\" :_]"
  [items])