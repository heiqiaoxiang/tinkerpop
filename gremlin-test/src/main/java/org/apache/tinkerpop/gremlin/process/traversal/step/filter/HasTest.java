/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.tinkerpop.gremlin.process.traversal.step.filter;

import org.apache.tinkerpop.gremlin.FeatureRequirement;
import org.apache.tinkerpop.gremlin.LoadGraphWith;
import org.apache.tinkerpop.gremlin.process.AbstractGremlinProcessTest;
import org.apache.tinkerpop.gremlin.process.GremlinProcessRunner;
import org.apache.tinkerpop.gremlin.process.IgnoreEngine;
import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.Traversal;
import org.apache.tinkerpop.gremlin.process.traversal.TraversalEngine;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.process.traversal.step.map.GraphStep;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Element;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.apache.tinkerpop.gremlin.LoadGraphWith.GraphData.CREW;
import static org.apache.tinkerpop.gremlin.LoadGraphWith.GraphData.MODERN;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 * @author Stephen Mallette (http://stephen.genoprime.com)
 */
@RunWith(GremlinProcessRunner.class)
public abstract class HasTest extends AbstractGremlinProcessTest {

    public abstract Traversal<Vertex, String> get_g_V_outXcreatedX_hasXname__mapXlengthX_isXgtX3XXX_name();

    public abstract Traversal<Vertex, Vertex> get_g_VX1X_hasXkeyX(final Object v1Id, final String key);

    public abstract Traversal<Vertex, Vertex> get_g_VX1X_hasXname_markoX(final Object v1Id);

    public abstract Traversal<Vertex, Vertex> get_g_V_hasXname_markoX();

    public abstract Traversal<Vertex, Vertex> get_g_V_hasXname_blahX();

    public abstract Traversal<Vertex, Vertex> get_g_V_hasXblahX();

    public abstract Traversal<Vertex, Vertex> get_g_VX1X_hasXage_gt_30X(final Object v1Id);

    public abstract Traversal<Vertex, Vertex> get_g_VXv1X_hasXage_gt_30X(final Object v1Id);

    public abstract Traversal<Vertex, Vertex> get_g_VX1X_out_hasXid_lt_3X(final Object v1Id, final Object v3Id);

    public abstract Traversal<Vertex, Vertex> get_g_VX1X_out_hasIdX2X(final Object v1Id, final Object v2Id);

    public abstract Traversal<Vertex, Vertex> get_g_VX1X_out_hasIdX2_3X(final Object v1Id, final Object v2Id, final Object v3Id);

    public abstract Traversal<Vertex, Vertex> get_g_V_hasXage_gt_30X();

    public abstract Traversal<Vertex, Vertex> get_g_V_hasXage_isXgt_30XX();

    public abstract Traversal<Edge, Edge> get_g_EX7X_hasLabelXknowsX(final Object e7Id);

    public abstract Traversal<Edge, Edge> get_g_E_hasLabelXknowsX();

    public abstract Traversal<Edge, Edge> get_g_EX11X_outV_outE_hasXid_10X(final Object e11Id, final Object e8Id);

    public abstract Traversal<Edge, Edge> get_g_E_hasLabelXuses_traversesX();

    public abstract Traversal<Vertex, Vertex> get_g_V_hasLabelXperson_software_blahX();

    public abstract Traversal<Vertex, Integer> get_g_V_hasXperson_name_markoX_age();

    public abstract Traversal<Vertex, Vertex> get_g_VX1X_outE_hasXweight_inside_0_06X_inV(final Object v1Id);

    public abstract Traversal<Vertex, Vertex> get_g_V_hasXlocationX();

    public abstract Traversal<Vertex, Vertex> get_g_VX1X(final Object v1Id);

    public abstract Traversal<Vertex, Vertex> get_g_V_hasIdX1X(final Object v1Id);

    public abstract Traversal<Vertex, Vertex> get_g_VX1_2X(final Object v1Id, final Object v2Id);

    public abstract Traversal<Vertex, Vertex> get_g_V_hasIdX1_2X(final Object v1Id, final Object v2Id);

    public abstract Traversal<Vertex, String> get_g_V_hasLabelXpersonX_hasXage_notXlteX10X_andXnotXbetweenX11_20XXXX_andXltX29X_orXeqX35XXXX_name();

    @Test
    @LoadGraphWith(MODERN)
    public void g_V_outXcreatedX_hasXname__mapXlengthX_isXgtX3XXX_name() {
        final Traversal<Vertex, String> traversal = get_g_V_outXcreatedX_hasXname__mapXlengthX_isXgtX3XXX_name();
        printTraversalForm(traversal);
        checkResults(Arrays.asList("ripple"), traversal);
    }

    @Test
    @LoadGraphWith(MODERN)
    public void g_VX1X_hasXkeyX() {
        Traversal<Vertex, Vertex> traversal = get_g_VX1X_hasXkeyX(convertToVertexId("marko"), "name");
        printTraversalForm(traversal);
        assertEquals("marko", traversal.next().<String>value("name"));
        assertFalse(traversal.hasNext());
        //
        traversal = get_g_VX1X_hasXkeyX(convertToVertexId("marko"), "circumference");
        printTraversalForm(traversal);
        assertFalse(traversal.hasNext());
    }

    @Test
    @LoadGraphWith(MODERN)
    public void g_VX1X_hasXname_markoX() {
        Traversal<Vertex, Vertex> traversal = get_g_VX1X_hasXname_markoX(convertToVertexId("marko"));
        printTraversalForm(traversal);
        assertEquals("marko", traversal.next().<String>value("name"));
        assertFalse(traversal.hasNext());
        traversal = get_g_VX1X_hasXname_markoX(convertToVertexId("vadas"));
        printTraversalForm(traversal);
        assertFalse(traversal.hasNext());
    }

    @Test
    @LoadGraphWith(MODERN)
    public void g_V_hasXname_markoX() {
        final Traversal<Vertex, Vertex> traversal = get_g_V_hasXname_markoX();
        printTraversalForm(traversal);
        assertEquals("marko", traversal.next().<String>value("name"));
        assertFalse(traversal.hasNext());
    }

    @Test
    @LoadGraphWith(MODERN)
    public void g_V_hasXname_blahX() {
        final Traversal<Vertex, Vertex> traversal = get_g_V_hasXname_blahX();
        printTraversalForm(traversal);
        assertFalse(traversal.hasNext());
    }

    @Test
    @LoadGraphWith(MODERN)
    public void g_V_hasXage_gt_30X() {
        final Traversal<Vertex, Vertex> traversal = get_g_V_hasXage_gt_30X();
        printTraversalForm(traversal);
        final List<Vertex> list = traversal.toList();
        assertEquals(2, list.size());
        for (final Element v : list) {
            assertTrue(v.<Integer>value("age") > 30);
        }
    }

    @Test
    @LoadGraphWith(MODERN)
    public void g_V_hasXage_isXgt_30XX() {
        final Traversal<Vertex, Vertex> traversal = get_g_V_hasXage_isXgt_30XX();
        printTraversalForm(traversal);
        final List<Vertex> list = traversal.toList();
        assertEquals(2, list.size());
        for (final Element v : list) {
            assertTrue(v.<Integer>value("age") > 30);
        }
    }

    @Test
    @LoadGraphWith(MODERN)
    public void g_VX1X_hasXage_gt_30X() {
        final Traversal<Vertex, Vertex> traversalMarko = get_g_VX1X_hasXage_gt_30X(convertToVertexId("marko"));
        printTraversalForm(traversalMarko);
        assertFalse(traversalMarko.hasNext());
        final Traversal<Vertex, Vertex> traversalJosh = get_g_VX1X_hasXage_gt_30X(convertToVertexId("josh"));
        printTraversalForm(traversalJosh);
        assertTrue(traversalJosh.hasNext());
    }

    @Test
    @LoadGraphWith(MODERN)
    public void g_VXv1X_hasXage_gt_30X() {
        final Traversal<Vertex, Vertex> traversalMarko = get_g_VXv1X_hasXage_gt_30X(convertToVertexId("marko"));
        printTraversalForm(traversalMarko);
        assertFalse(traversalMarko.hasNext());
        final Traversal<Vertex, Vertex> traversalJosh = get_g_VXv1X_hasXage_gt_30X(convertToVertexId("josh"));
        printTraversalForm(traversalJosh);
        assertTrue(traversalJosh.hasNext());
    }

    @Test
    @LoadGraphWith(MODERN)
    public void g_VX1X_out_hasXid_2X() {
        final Traversal<Vertex, Vertex> traversal = get_g_VX1X_out_hasIdX2X(convertToVertexId("marko"), convertToVertexId("vadas"));
        assertVadasAsOnlyValueReturned(traversal);
    }

    @Test
    @LoadGraphWith(MODERN)
    @FeatureRequirement(featureClass = Graph.Features.VertexFeatures.class, feature = Graph.Features.VertexFeatures.FEATURE_USER_SUPPLIED_IDS)
    @FeatureRequirement(featureClass = Graph.Features.VertexFeatures.class, feature = Graph.Features.VertexFeatures.FEATURE_NUMERIC_IDS)
    public void g_VX1X_out_hasXid_lt_3X() {
        // can only execute this on graphs with user supplied ids so that we can be assured of the lt op. it
        // sort of assumes that ids increment, but there's no feature check for that.  graphs that don't work this
        // way with numeric ids may need to optout
        final Traversal<Vertex, Vertex> traversal = get_g_VX1X_out_hasXid_lt_3X(convertToVertexId("marko"), convertToVertexId("lop"));
        assertVadasAsOnlyValueReturned(traversal);
    }

    @Test
    @LoadGraphWith(MODERN)
    public void g_VX1AsStringX_out_hasXid_2AsStringX() {
        final Traversal<Vertex, Vertex> traversal = get_g_VX1X_out_hasIdX2X(convertToVertexId("marko").toString(), convertToVertexId("vadas").toString());
        assertVadasAsOnlyValueReturned(traversal);
    }

    private void assertVadasAsOnlyValueReturned(final Traversal<Vertex, Vertex> traversal) {
        printTraversalForm(traversal);
        assertThat(traversal.hasNext(), is(true));
        assertEquals(convertToVertexId("vadas"), traversal.next().id());
        assertThat(traversal.hasNext(), is(false));
    }

    @Test
    @LoadGraphWith(MODERN)
    public void g_VX1X_out_hasXid_2_3X() {
        final Object id2 = convertToVertexId("vadas");
        final Object id3 = convertToVertexId("lop");
        final Traversal<Vertex, Vertex> traversal = get_g_VX1X_out_hasIdX2_3X(convertToVertexId("marko"), id2, id3);
        assert_g_VX1X_out_hasXid_2_3X(id2, id3, traversal);
    }

    @Test
    @LoadGraphWith(MODERN)
    public void g_VX1X_out_hasXid_2AsString_3AsStringX() {
        final Object id2 = convertToVertexId("vadas");
        final Object id3 = convertToVertexId("lop");
        final Traversal<Vertex, Vertex> traversal = get_g_VX1X_out_hasIdX2_3X(convertToVertexId("marko"), id2.toString(), id3.toString());
        assert_g_VX1X_out_hasXid_2_3X(id2, id3, traversal);
    }

    protected void assert_g_VX1X_out_hasXid_2_3X(Object id2, Object id3, Traversal<Vertex, Vertex> traversal) {
        printTraversalForm(traversal);
        assertTrue(traversal.hasNext());
        assertThat(traversal.next().id(), CoreMatchers.anyOf(is(id2), is(id3)));
        assertThat(traversal.next().id(), CoreMatchers.anyOf(is(id2), is(id3)));
        assertFalse(traversal.hasNext());
    }

    @Test
    @LoadGraphWith(MODERN)
    public void g_V_hasXblahX() {
        //assumeTrue(graphMeetsTestRequirements());
        final Traversal<Vertex, Vertex> traversal = get_g_V_hasXblahX();
        printTraversalForm(traversal);
        assertFalse(traversal.hasNext());
    }


    @Test
    @LoadGraphWith(MODERN)
    public void g_EX7X_hasXlabelXknowsX() {
        //System.out.println(convertToEdgeId("marko", "knows", "vadas"));
        final Traversal<Edge, Edge> traversal = get_g_EX7X_hasLabelXknowsX(convertToEdgeId("marko", "knows", "vadas"));
        printTraversalForm(traversal);
        int counter = 0;
        while (traversal.hasNext()) {
            counter++;
            assertEquals("knows", traversal.next().label());
        }
        assertEquals(1, counter);
    }

    @Test
    @LoadGraphWith(MODERN)
    public void g_E_hasXlabelXknowsX() {
        final Traversal<Edge, Edge> traversal = get_g_E_hasLabelXknowsX();
        printTraversalForm(traversal);
        int counter = 0;
        while (traversal.hasNext()) {
            counter++;
            assertEquals("knows", traversal.next().label());
        }
        assertEquals(2, counter);
    }

    @Test
    @LoadGraphWith(CREW)
    public void g_E_hasLabelXuses_traversesX() {
        final Traversal<Edge, Edge> traversal = get_g_E_hasLabelXuses_traversesX();
        printTraversalForm(traversal);
        int counter = 0;
        while (traversal.hasNext()) {
            counter++;
            final String label = traversal.next().label();
            assertTrue(label.equals("uses") || label.equals("traverses"));
        }
        assertEquals(9, counter);
    }

    @Test
    @LoadGraphWith(CREW)
    public void g_V_hasLabelXperson_software_blahX() {
        final Traversal<Vertex, Vertex> traversal = get_g_V_hasLabelXperson_software_blahX();
        printTraversalForm(traversal);
        int counter = 0;
        while (traversal.hasNext()) {
            counter++;
            final String label = traversal.next().label();
            assertTrue(label.equals("software") || label.equals("person"));
        }
        assertEquals(6, counter);
    }

    @Test
    @LoadGraphWith(MODERN)
    public void g_V_hasXperson_name_markoX_age() {
        final Traversal<Vertex, Integer> traversal = get_g_V_hasXperson_name_markoX_age();
        printTraversalForm(traversal);
        assertEquals(29, traversal.next().intValue());
        assertFalse(traversal.hasNext());
    }

    @Test
    @LoadGraphWith(MODERN)
    public void g_VX1X_outE_hasXweight_inside_0_06X_inV() {
        final Traversal<Vertex, Vertex> traversal = get_g_VX1X_outE_hasXweight_inside_0_06X_inV(convertToVertexId("marko"));
        printTraversalForm(traversal);
        while (traversal.hasNext()) {
            Vertex vertex = traversal.next();
            assertTrue(vertex.value("name").equals("vadas") || vertex.value("name").equals("lop"));
        }
        assertFalse(traversal.hasNext());
    }

    @Test
    @LoadGraphWith(MODERN)
    public void g_EX11X_outV_outE_hasXid_10X() {
        final Object edgeId11 = convertToEdgeId("josh", "created", "lop");
        final Object edgeId10 = convertToEdgeId("josh", "created", "ripple");
        final Traversal<Edge, Edge> traversal = get_g_EX11X_outV_outE_hasXid_10X(edgeId11, edgeId10);
        printTraversalForm(traversal);
        assert_g_EX11X(edgeId10, traversal);
    }

    @Test
    @LoadGraphWith(MODERN)
    public void g_EX11X_outV_outE_hasXid_10AsStringX() {
        final Object edgeId11 = convertToEdgeId("josh", "created", "lop");
        final Object edgeId10 = convertToEdgeId("josh", "created", "ripple");
        final Traversal<Edge, Edge> traversal = get_g_EX11X_outV_outE_hasXid_10X(edgeId11.toString(), edgeId10.toString());
        printTraversalForm(traversal);
        assert_g_EX11X(edgeId10, traversal);
    }

    @Test
    @LoadGraphWith(CREW)
    public void g_V_hasXlocationX() {
        final Traversal<Vertex, Vertex> traversal = get_g_V_hasXlocationX();
        printTraversalForm(traversal);
        checkResults(Arrays.asList(convertToVertex(graph, "marko"), convertToVertex(graph, "stephen"), convertToVertex(graph, "daniel"), convertToVertex(graph, "matthias")), traversal);
    }

    @Test
    @LoadGraphWith(MODERN)
    @IgnoreEngine(TraversalEngine.Type.COMPUTER) // only validate for OLTP
    public void g_V_hasId_compilationEquality() {
        final Traversal<Vertex, Vertex> traversala1 = get_g_VX1X(convertToVertexId("marko"));
        final Traversal<Vertex, Vertex> traversala2 = get_g_V_hasIdX1X(convertToVertexId("marko"));
        final Traversal<Vertex, Vertex> traversalb1 = get_g_VX1_2X(convertToVertexId("marko"), convertToVertexId("vadas"));
        final Traversal<Vertex, Vertex> traversalb2 = get_g_V_hasIdX1_2X(convertToVertexId("marko"), convertToVertexId("vadas"));
        printTraversalForm(traversala1);
        printTraversalForm(traversala2);
        printTraversalForm(traversalb1);
        printTraversalForm(traversalb2);
        checkResults(Collections.singletonList(convertToVertex(graph, "marko")), traversala1);
        checkResults(Collections.singletonList(convertToVertex(graph, "marko")), traversala2);
        checkResults(Arrays.asList(convertToVertex(graph, "marko"), convertToVertex(graph, "vadas")), traversalb1);
        checkResults(Arrays.asList(convertToVertex(graph, "marko"), convertToVertex(graph, "vadas")), traversalb2);
        // if providers don't have their own custom GraphStep, then ignore validating compilation equality
        if ((traversala1.asAdmin().getStartStep() instanceof GraphStep) &&
                !traversala1.asAdmin().getStartStep().getClass().equals(GraphStep.class)) {
            assertEquals(traversala1, traversala2);
            assertEquals(traversalb1, traversalb2);
            assertNotEquals(traversala1, traversalb1);
            assertNotEquals(traversala1, traversalb2);
            assertNotEquals(traversala2, traversalb1);
            assertNotEquals(traversala2, traversalb2);
        }
    }

    @Test
    @LoadGraphWith(MODERN)
    public void g_V_hasLabelXpersonX_hasXage_notXlteX10X_andXnotXbetweenX11_20XXXX_andXltX29X_orXeqX35XXXX_name() {
        final Traversal<Vertex, String> traversal = get_g_V_hasLabelXpersonX_hasXage_notXlteX10X_andXnotXbetweenX11_20XXXX_andXltX29X_orXeqX35XXXX_name();
        printTraversalForm(traversal);
        checkResults(Arrays.asList("peter", "vadas"), traversal);
    }

    private void assert_g_EX11X(final Object edgeId, final Traversal<Edge, Edge> traversal) {
        printTraversalForm(traversal);
        assertTrue(traversal.hasNext());
        final Edge e = traversal.next();
        assertEquals(edgeId, e.id());
        assertFalse(traversal.hasNext());
    }

    public static class Traversals extends HasTest {
        @Override
        public Traversal<Edge, Edge> get_g_EX11X_outV_outE_hasXid_10X(final Object e11Id, final Object e8Id) {
            return g.E(e11Id).outV().outE().has(T.id, e8Id);
        }

        @Override
        public Traversal<Vertex, String> get_g_V_outXcreatedX_hasXname__mapXlengthX_isXgtX3XXX_name() {
            return g.V().out("created").has("name", __.<String, Integer>map(s -> s.get().length()).is(P.gt(3))).values("name");
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_VX1X_hasXkeyX(final Object v1Id, final String key) {
            return g.V(v1Id).has(key);
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_VX1X_hasXname_markoX(final Object v1Id) {
            return g.V(v1Id).has("name", "marko");
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasXname_markoX() {
            return g.V().has("name", "marko");
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasXname_blahX() {
            return g.V().has("name", "blah");
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasXblahX() {
            return g.V().has("blah");
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_VX1X_hasXage_gt_30X(final Object v1Id) {
            return g.V(v1Id).has("age", P.gt(30));
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_VXv1X_hasXage_gt_30X(final Object v1Id) {
            return g.V(g.V(v1Id).next()).has("age", P.gt(30));
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_VX1X_out_hasXid_lt_3X(final Object v1Id, final Object v3Id) {
            return g.V(v1Id).out().has(T.id, P.lt(v3Id));
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_VX1X_out_hasIdX2X(final Object v1Id, final Object v2Id) {
            return g.V(v1Id).out().hasId(v2Id);
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_VX1X_out_hasIdX2_3X(final Object v1Id, final Object v2Id, final Object v3Id) {
            return g.V(v1Id).out().hasId(v2Id, v3Id);
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasXage_gt_30X() {
            return g.V().has("age", P.gt(30));
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasXage_isXgt_30XX() {
            return g.V().has("age", __.is(P.gt(30)));
        }

        @Override
        public Traversal<Edge, Edge> get_g_EX7X_hasLabelXknowsX(final Object e7Id) {
            return g.E(e7Id).hasLabel("knows");
        }

        @Override
        public Traversal<Edge, Edge> get_g_E_hasLabelXknowsX() {
            return g.E().hasLabel("knows");
        }

        @Override
        public Traversal<Edge, Edge> get_g_E_hasLabelXuses_traversesX() {
            return g.E().hasLabel("uses", "traverses");
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasLabelXperson_software_blahX() {
            return g.V().hasLabel("person", "software", "blah");
        }

        @Override
        public Traversal<Vertex, Integer> get_g_V_hasXperson_name_markoX_age() {
            return g.V().has("person", "name", "marko").values("age");
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_VX1X_outE_hasXweight_inside_0_06X_inV(final Object v1Id) {
            return g.V(v1Id).outE().has("weight", P.inside(0.0d, 0.6d)).inV();
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasXlocationX() {
            return g.V().has("location");
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_VX1X(final Object v1Id) {
            return g.V(v1Id);
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasIdX1X(final Object v1Id) {
            return g.V().hasId(v1Id);
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_VX1_2X(final Object v1Id, final Object v2Id) {
            return g.V(v1Id, v2Id);
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasIdX1_2X(final Object v1Id, final Object v2Id) {
            return g.V().hasId(v1Id, v2Id);
        }

        @Override
        public Traversal<Vertex, String> get_g_V_hasLabelXpersonX_hasXage_notXlteX10X_andXnotXbetweenX11_20XXXX_andXltX29X_orXeqX35XXXX_name() {
            return g.V().hasLabel("person").has("age", P.not(P.lte(10).and(P.not(P.between(11, 20)))).and(P.lt(29).or(P.eq(35)))).values("name");
        }
    }
}
