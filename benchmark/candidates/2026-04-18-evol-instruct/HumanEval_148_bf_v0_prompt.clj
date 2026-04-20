(defn kth-between
  "Given a vector of planet names in order from the Sun and two indices k1 and k2,
   return a vector of the planets whose positions are strictly between k1 and k2.
   Indices are zero-based. If either index is out of bounds, negative, or the same,
   return an empty vector.

   Examples:
   >>> (kth-between [\"Mercury\" \"Venus\" \"Earth\" \"Mars\" \"Jupiter\" \"Saturn\" \"Uranus\" \"Neptune\"] 1 4)
   [\"Earth\" \"Mars\"]
   >>> (kth-between [\"Mercury\" \"Venus\" \"Earth\" \"Mars\" \"Jupiter\" \"Saturn\" \"Uranus\" \"Neptune\"] 6 2)
   [\"Mars\" \"Jupiter\" \"Saturn\"]
   >>> (kth-between [\"Mercury\" \"Venus\" \"Earth\"] 0 0)
   []"
  [planets k1 k2])